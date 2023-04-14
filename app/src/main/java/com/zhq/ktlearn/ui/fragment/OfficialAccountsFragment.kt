package com.zhq.ktlearn.ui.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zhq.ktlearn.R
import com.zhq.commonlib.base.BaseVMFragment
import com.zhq.ktlearn.databinding.FragmentOfficialAccountsBinding
import kotlinx.android.synthetic.main.fragment_official_accounts.*

/**
 * @Author ZhangHuiQiang
 * @Date 2023/3/3 10:16
 * Description
 */
class OfficialAccountsFragment : BaseVMFragment<FragmentOfficialAccountsBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_official_accounts
    }

    private lateinit var contactsAdapter: ArrayAdapter<String>
    private val contactsList = ArrayList<String>()
    override fun initView() {
        contactsAdapter = ArrayAdapter(mContext, android.R.layout.simple_list_item_1, contactsList)
        list_view.adapter = contactsAdapter
        if (ContextCompat.checkSelfPermission(
                mContext,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_CONTACTS), 1
            )
        } else {
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readContacts()
            }
        }
    }

    @SuppressLint("Range")
    private fun readContacts() {
// 查询联系人数据
        mContext.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null, null, null, null
        )?.apply {
            while (moveToNext()) {
                // 获取联系人姓名
                val displayName = getString(
                    getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                    )
                )
                // 获取联系人手机号
                val number = getString(
                    getColumnIndex(
                        ContactsContract.CommonDataKinds.Phone.NUMBER
                    )
                )
                contactsList.add("$displayName\n$number")
            }
            Log.d(TAG, "===readContacts: "+contactsList.size)
            contactsAdapter.notifyDataSetChanged()
            close()
        }
    }

    override fun observe() {

    }

}