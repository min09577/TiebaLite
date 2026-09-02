package com.huanchengfly.tieba.post.api.adapters

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import com.huanchengfly.tieba.post.api.models.MessageListBean.AgreeBean
import java.lang.reflect.Type

class AgreeMeListAdapter : JsonDeserializer<List<AgreeBean>> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): List<AgreeBean> {
        return if (json.isJsonPrimitive) {
            ArrayList()
        } else context.deserialize(json, typeOfT)
    }
}
