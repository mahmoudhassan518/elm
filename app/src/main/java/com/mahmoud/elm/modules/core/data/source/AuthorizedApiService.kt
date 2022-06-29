package com.mahmoud.elm.modules.core.data.source

import com.mahmoud.elm.modules.incidents.list.data.model.IncidentsResponse
import com.mahmoud.elm.modules.incidents.submit.domain.domain.SubmitIncidentPram
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthorizedApiService {


    @GET("incident")
    suspend fun getIncidents(
        @Query("startDate") startDate: String?,
        @Query("status") status: String?
    ): IncidentsResponse

    @POST("submit-incident") // not exist so it will always gives an error
    suspend fun submitIncident(
        @Body param: SubmitIncidentPram,
    )


}
