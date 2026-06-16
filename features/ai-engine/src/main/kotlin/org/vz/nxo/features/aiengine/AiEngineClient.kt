package org.vz.nxo.features.aiengine

import com.google.ai.client.generativeai.GenerativeModel

class AiEngineClient {
    fun getModel(apiKey: String): GenerativeModel {
        return GenerativeModel(
            modelName = "gemini-3.1-flash",
            apiKey = apiKey
        )
    }

    suspend fun suggestAppSorting(apiKey: String, apps: List<String>): List<String> {
        val model = getModel(apiKey)
        val prompt = "Sort these Android apps by common usage patterns for a power user: ${apps.joinToString(", не")}. Return only the sorted list separated by commas."
        val response = model.generateContent(prompt)
        return response.text?.split(",")?.map { it.trim() } ?: apps
    }
}
