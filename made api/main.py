from fastapi import FastAPI
from datetime import datetime, timedelta
import yfinance as yf
import math

app = FastAPI()

# 20개 주식의 심볼을 리스트로 정의
symbols = [
    "005930.KS", "086520.KQ", "373220.KS", "000660.KS", "207940.KS",
    "005490.KS", "005380.KS", "051910.KS", "006400.KS", "000270.KS",
    "035420.KS", "003670.KS", "105560.KS", "012330.KS", "068270.KS",
    "028260.KS", "035720.KS", "055550.KS", "066570.KS", "032830.KS"
]

@app.get("/stock-price/{symbol}")
def get_stock_price(symbol: str):
    try:
        if symbol in symbols:
            # Ticker 객체를 생성하고 주식 데이터 가져오기
            ticker = yf.Ticker(symbol)
            data = ticker.history(period="1d")
            current_price = data["Close"].iloc[0]

            return {"symbol": symbol, "price": current_price}
        else:
            return {"error": "Invalid symbol"}
    except Exception as e:
        return {"error": str(e)}


@app.get("/historical-stock-prices/{symbol}")
def get_historical_stock_prices(symbol: str):
    try:
        # 1년 전 날짜 계산
        one_year_ago = datetime.now() - timedelta(days=365)

        # YFinance를 사용하여 주식 데이터 가져오기
        stock = yf.Ticker(symbol)
        data = stock.history(period="1d", start=one_year_ago, end=datetime.now())

        if not data.empty:
            # 날짜별 주가 데이터를 리스트로 반환
            historical_prices = [{"Date": date.strftime("%Y-%m-%d"),
                     "Close": int(close),
                     "Low": int(low),
                     "High": int(high),
                     "Open": int(open)}
                    for date, close, low, high, open in zip(data.index, data["Close"], data["Low"], data["High"], data["Open"])]
            return historical_prices
        else:
            return {"error": "No data available for the given symbol"}
    except Exception as e:
        return {"error": str(e)}
