package tqs.lab2;

import java.util.List;
import java.util.ArrayList;
/**
 * Hello world!
 *
 */
public class StocksPortfolio
{
    private String name;
    private List<Stock> allStocks = new ArrayList();
    private IStockMarket Imarket;
    public StocksPortfolio(String name){
        this.name=name;
    }

    public IStockMarket getMarketService(){
        return Imarket;
    }

    public void setMarketService(IStockMarket stock){
        this.Imarket=stock;
    }

    public String getName(){
        return this.name;
    }

    public double getTotalValue(){
        double res = 0;
        double part = 0;
        for(Stock st : this.allStocks){
            part=this.Imarket.getPrice(st.getLabel());
            res+=part*st.getQuantity();
        }
        return res;
    }

    public void addStock(Stock stock){
        allStocks.add(stock);
    }
}
