package com.example.maktab.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

private class ExampleConfig {
    @Value("secret.my-key")
    lateinit var mySecretKey: String;

    init {
        println(mySecretKey);
    }
}