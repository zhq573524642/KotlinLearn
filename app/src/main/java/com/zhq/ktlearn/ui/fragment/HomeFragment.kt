package com.zhq.ktlearn.ui.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.core.content.FileProvider
import com.permissionx.guolindev.PermissionX
import com.zhq.ktlearn.R
import com.zhq.ktlearn.base.BaseVMFragment
import com.zhq.ktlearn.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.*
import kotlin.math.log

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/3 10:15
 * Description
 */
class HomeFragment : BaseVMFragment<FragmentHomeBinding>() {
    companion object {
        private const val TAG = "HomeFragment"
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        btnSaveContent.setOnClickListener {
            val content = etInput.text.toString()
            if (TextUtils.isEmpty(content)) {
                Toast.makeText(mContext, "请输入内容", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Log.d(TAG, "===initView: ")
            save(content)
        }

        btnGetContent.setOnClickListener {
            val content = read()
            if (!TextUtils.isEmpty(content)) {
                tvGetContent.text = content
            }
        }

        btnCamera.setOnClickListener {
            PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA/*,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE*/)
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        openCamera()
                    }
                }
        }

        btnGallery.setOnClickListener {
            openAlbum()
        }
    }


    private fun save(inputText: String) {
        try {
            val openFileOutput = activity?.openFileOutput("myData", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(openFileOutput))
            writer.use {
                it.write(inputText)
            }
        } catch (e: IOException) {
        }
    }

    private fun read(): String {
        val content = StringBuilder()
        try {
            val openFileInput = activity?.openFileInput("myData")
            val reader = BufferedReader(InputStreamReader(openFileInput))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException) {
        }
        return content.toString()
    }

    lateinit var imageFile: File
    lateinit var imageUri: Uri
    private fun openCamera() {
        imageFile = File(mContext.externalCacheDir, "cameraImg.jpg")
        if (imageFile.exists()) {
            imageFile.delete()
        }
        imageFile.createNewFile()
        imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(mContext, mContext.packageName + ".fileprovider", imageFile)
        } else {
            Uri.fromFile(imageFile)
        }
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(intent, 1001)
    }

    private fun openAlbum() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, 1002)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            1001 -> if (resultCode == Activity.RESULT_OK) {
                val bitmap =
                    BitmapFactory.decodeStream(mContext.contentResolver.openInputStream(imageUri))
                if (bitmap != null) {
                    ivPhoto.setImageBitmap(rotateIfRequired(bitmap))
                }
            }

            1002 -> if (resultCode == Activity.RESULT_OK && data != null) {
                data.data?.let { uri ->
                    val bitmap = getBitmapFromUri(uri)
                    ivPhoto.setImageBitmap(bitmap)
                }
            }

        }
    }

    private fun getBitmapFromUri(uri: Uri) =
        mContext.contentResolver.openFileDescriptor(uri, "r")?.use {
            BitmapFactory.decodeFileDescriptor(it.fileDescriptor)
        }

    private fun rotateIfRequired(bitmap: Bitmap): Bitmap {
        val exif = ExifInterface(imageFile.path)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotateBitmap(bitmap, 90)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotateBitmap(bitmap, 180)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotateBitmap(bitmap, 270)
            else -> bitmap
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, degree: Int): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(degree.toFloat())
        val rotatedBitmap = Bitmap.createBitmap(
            bitmap, 0, 0, bitmap.width, bitmap.height,
            matrix, true
        )
        bitmap.recycle() // 将不再需要的Bitmap对象回收
        return rotatedBitmap
    }


}