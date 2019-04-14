package lyx.miaosha.pojo;

import java.sql.Timestamp;

/**
 * @Title miaoshagoods
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\2 0002 15:17
 */
public class miaoshagoods {
    private Long id;
    private Long goodsid;
    private Integer stockCount;
    private Timestamp startDate;
    private Timestamp endDate;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getGoodsId() {
        return goodsid;
    }
    public void setGoodsId(Long goodsid) {
        this.goodsid = goodsid;
    }
    public Integer getStockCount() {
        return stockCount;
    }
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }
    public Timestamp getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    public Timestamp getEndDate() {
        return endDate;
    }
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}
