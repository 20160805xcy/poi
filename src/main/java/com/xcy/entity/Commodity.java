package com.xcy.entity;

import java.math.BigDecimal;

/**
 * @author xcy
 * @date 2018/09/28 14:26
 * @description 商品
 * @since V1.0.0
 */
public class Commodity {
    private String commodityModel;
    private String commodityName;
    private Integer commodityNum;
    private String commodityUnit;
    private BigDecimal commodityUnitPrice;
    private String commodityBrand;
    private String commodityPlace;
    private String commodityCode;
    private BigDecimal commodityNetWeight;
    private BigDecimal commodityGrossWeight;
    private Integer commodityBoxNum;
    private String commodityPo;
    private Integer commodityBatchNum;
    private String commodityReceiveAddress;
    private String commodityLogisticsDealer;

    public String getCommodityModel() {
        return commodityModel;
    }

    public void setCommodityModel(String commodityModel) {
        this.commodityModel = commodityModel;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    public String getCommodityUnit() {
        return commodityUnit;
    }

    public void setCommodityUnit(String commodityUnit) {
        this.commodityUnit = commodityUnit;
    }

    public BigDecimal getCommodityUnitPrice() {
        return commodityUnitPrice;
    }

    public void setCommodityUnitPrice(BigDecimal commodityUnitPrice) {
        this.commodityUnitPrice = commodityUnitPrice;
    }

    public String getCommodityBrand() {
        return commodityBrand;
    }

    public void setCommodityBrand(String commodityBrand) {
        this.commodityBrand = commodityBrand;
    }

    public String getCommodityPlace() {
        return commodityPlace;
    }

    public void setCommodityPlace(String commodityPlace) {
        this.commodityPlace = commodityPlace;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
    }

    public BigDecimal getCommodityNetWeight() {
        return commodityNetWeight;
    }

    public void setCommodityNetWeight(BigDecimal commodityNetWeight) {
        this.commodityNetWeight = commodityNetWeight;
    }

    public BigDecimal getCommodityGrossWeight() {
        return commodityGrossWeight;
    }

    public void setCommodityGrossWeight(BigDecimal commodityGrossWeight) {
        this.commodityGrossWeight = commodityGrossWeight;
    }

    public Integer getCommodityBoxNum() {
        return commodityBoxNum;
    }

    public void setCommodityBoxNum(Integer commodityBoxNum) {
        this.commodityBoxNum = commodityBoxNum;
    }

    public String getCommodityPo() {
        return commodityPo;
    }

    public void setCommodityPo(String commodityPo) {
        this.commodityPo = commodityPo;
    }

    public Integer getCommodityBatchNum() {
        return commodityBatchNum;
    }

    public void setCommodityBatchNum(Integer commodityBatchNum) {
        this.commodityBatchNum = commodityBatchNum;
    }

    public String getCommodityReceiveAddress() {
        return commodityReceiveAddress;
    }

    public void setCommodityReceiveAddress(String commodityReceiveAddress) {
        this.commodityReceiveAddress = commodityReceiveAddress;
    }

    public String getCommodityLogisticsDealer() {
        return commodityLogisticsDealer;
    }

    public void setCommodityLogisticsDealer(String commodityLogisticsDealer) {
        this.commodityLogisticsDealer = commodityLogisticsDealer;
    }
}
