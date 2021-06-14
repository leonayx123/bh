package com.thoughtworks.bh.api.entrust.reponse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

/**
 * @Description
 * @Author yangxun xun.yang@thoughtworks.com
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2021/6/14
 */
@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class GetEntrustResponse {


    @ApiModelProperty(value = "拍品状态")
    public String status;

    @ApiModelProperty(value = "拍品登记信息")
    public EntrustRfp entrustRfp;

    @Getter
    @Setter
    public static class EntrustRfp {
        @ApiModelProperty(value = "拍品名称")
        private String name;

        @ApiModelProperty(value = "拍品年份")
        private String time;

        @ApiModelProperty(value = "拍品类型")
        private String type;

        @ApiModelProperty(value = "期望价格")
        private BigDecimal requirePrice;

        @ApiModelProperty(value = "联系人电话")
        private String phone;

        @ApiModelProperty(value = "联系人姓名")
        private String userName;
    }
}
