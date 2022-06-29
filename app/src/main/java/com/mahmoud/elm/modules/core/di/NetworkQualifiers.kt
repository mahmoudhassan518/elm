package com.mahmoud.elm.modules.core.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserManagementOkhttpInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthorizedOkhttpInterceptor


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserManagementOkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthorizedOkhttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserManagementRetrofitClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthorizedRetrofitClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class UserManagementApiInterface

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthorizedApiInterface