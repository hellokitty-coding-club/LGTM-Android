package com.lgtm.android.data.deserializer

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.lgtm.domain.entity.response.SduiItemVO
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiTheme
import com.lgtm.domain.server_drive_ui.SduiViewType
import com.lgtm.domain.server_drive_ui.SduiViewType.Companion.getViewTypeClassType
import java.lang.reflect.Type

class SduiViewTypeDeserializer : JsonDeserializer<SduiItemVO> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement?, typeOfT: Type, context: JsonDeserializationContext
    ): SduiItemVO {
        val jsonObject = json?.asJsonObject ?: throw IllegalArgumentException("Json Parsing 실패")
        val viewTypeString: String = jsonObject.get("viewTypeName").asString
        val viewType: SduiViewType = SduiViewType.findClassByItsName(viewTypeString)
        val themeString: String = jsonObject.get("theme").asString
        val theme: SduiTheme = SduiTheme.findClassByItsName(themeString)
        val decidedViewType = viewType.getViewTypeClassType()
        val content = jsonObject.get("content").asJsonObject
        val sduiContent: SduiContent = Gson().fromJson(content, decidedViewType)
        return SduiItemVO(viewType, theme, sduiContent)
    }
}