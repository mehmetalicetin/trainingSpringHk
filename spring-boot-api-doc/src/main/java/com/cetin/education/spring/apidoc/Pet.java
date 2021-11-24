package com.cetin.education.spring.apidoc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Benim Pet nesnem", description = "Benim Pet")
public class Pet {

    @ApiModelProperty(value = "Pet nesenesinin tekil id alanı", name = "Pet nesenesinin tekil id alanı name")
    private int id;

    @ApiModelProperty(value = "Pet nesenesinin adi alanı", name = "Pet nesenesinin adi alanı name")
    private String name;

    @ApiModelProperty(value = "Pet nesenesinin tarih alanı", name = "Pet nesenesinin tarih alanı name")
    private Date date;
}
