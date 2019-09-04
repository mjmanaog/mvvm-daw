package com.example.mvvmsampleproject.helper

import com.example.mvvmsampleproject.model.Body
import com.example.mvvmsampleproject.model.Footer
import com.example.mvvmsampleproject.model.Header

abstract class CustomViewHelper(
    val updateType: String,val uniqueId: Int, val cid: Int ) {
    class TYPE {
        companion object {
            val HEADER = "header"
            val BODY = "body"
            val FOOTER = "footer"
        }
    }
    data class HeaderCustomViewHelper(
        val headerCId: Int,
        val headerUniqueId: Int,
        val header: Header
    ) : CustomViewHelper(TYPE.HEADER, headerUniqueId,headerCId)
    data class FooterCustomViewHelper(
        val footerCId: Int,
        val footerUniqueId: Int,
        val footer: Footer
    ) : CustomViewHelper(TYPE.FOOTER, footerUniqueId,footerCId)
    data class BodyCustomViewHelper(
        val bodyCId: Int,
        val bodyUniqueId: Int,
        val body: Body
    ) : CustomViewHelper(TYPE.BODY, bodyUniqueId,bodyCId)
}