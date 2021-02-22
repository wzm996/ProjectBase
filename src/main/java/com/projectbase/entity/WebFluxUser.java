package com.projectbase.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tianren
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class WebFluxUser implements Serializable {

    private String name;
    private Integer age;

}
