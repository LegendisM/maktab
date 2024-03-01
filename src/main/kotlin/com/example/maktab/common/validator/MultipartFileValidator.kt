package com.example.maktab.common.validator

import com.example.maktab.common.annotation.ValidMultipartFile
import com.example.maktab.common.constant.MultipartFileConstant
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.web.multipart.MultipartFile

class MultipartFileValidator : ConstraintValidator<ValidMultipartFile, MultipartFile> {
    override fun isValid(value: MultipartFile?, context: ConstraintValidatorContext?): Boolean {
        val isValid = MultipartFileConstant.VALID_CONTENT_TYPES.contains(value?.contentType)

        if (!isValid) {
            // TODO: customize exception message
            context?.disableDefaultConstraintViolation()
            context?.buildConstraintViolationWithTemplate(
                "Invalid multipart-file content type (${MultipartFileConstant.VALID_CONTENT_TYPES_MESSAGE})"
            )?.addConstraintViolation()

            return false
        } else {
            return true
        }
    }
}