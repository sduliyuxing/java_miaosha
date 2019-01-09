package lyx.miaosha.pojo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @Title goodsvo
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\2 0002 15:20
 */
public class goodsvo extends goods {
    private Double miaoshaPrice;
    private Integer stockCount;
    private Timestamp startDate;
    private Timestamp endDate;
    public Integer getStockCount() {
        return stockCount;
    }
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
    public Double getMiaoshaPrice() {
        return miaoshaPrice;
    }
    public void setMiaoshaPrice(Double miaoshaPrice) {
        this.miaoshaPrice = miaoshaPrice;
    }
}
