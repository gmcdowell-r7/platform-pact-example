package com.rapid7.example.pact.swagger.model;

import com.rapid7.example.pact.swagger.invoker.StringUtil;
import com.rapid7.example.pact.swagger.model.Animal;
import java.util.ArrayList;
import java.util.*;



import io.swagger.annotations.*;
import com.fasterxml.jackson.annotation.JsonProperty;


@ApiModel(description = "")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2018-03-12T15:39:14.363Z")
public class Animals extends ArrayList<Animal>  {
  

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Animals {\n");
    sb.append("    ").append(StringUtil.toIndentedString(super.toString())).append("\n");
    sb.append("}");
    return sb.toString();
  }
}
