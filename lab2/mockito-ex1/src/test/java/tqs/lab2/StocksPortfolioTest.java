package tqs.lab2;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;

public class StocksPortfolioTest
{
    private static final double DELTA = 1e-15;
    
    @Test
    public void getTotalValueTest(){

        IStockMarket market= mock(IStockMarket.class);
        StocksPortfolio portfolio = new StocksPortfolio("port1");
        portfolio.setMarketService(market);

        portfolio.addStock(new Stock("EDP",1));
        portfolio.addStock(new Stock("MSFT",1));
        
        when(market.getPrice("EDP")).thenReturn(1.2);
        when(market.getPrice("MSFT")).thenReturn(2.4);
        //2 vezes
        assertEquals(portfolio.getTotalValue(),3.6, DELTA);

        portfolio.addStock(new Stock("CHNL",3));
        when(market.getPrice("CHNL")).thenReturn(3.1);
        //3 vezes
        assertThat(portfolio.getTotalValue(),is(12.9));
        verify( market, times(5)).getPrice(anyString());
    }

    @Test
    public void getTotalValueTestWrongLabel(){

        IStockMarket market = mock(IStockMarket.class);
        StocksPortfolio  portfolio2 = new StocksPortfolio("port1");

        portfolio2.setMarketService(market);
        portfolio2.addStock(new Stock("EDPS",1));
        portfolio2.addStock(new Stock("MSFTA",1));

        when(market.getPrice("EDP")).thenReturn(1.2);
        when(market.getPrice("MSFT")).thenReturn(2.4);
        //2 vezes
        assertThat(portfolio2.getTotalValue(),is(0.0));
        verify( market, times(2)).getPrice(anyString());
    }
}
