using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

[System.Serializable]
public class UserInfo
{
    public string user_id;
    public string email;
    public string stock_id;
    public string nickname;
}

[System.Serializable]
public class StockAccount
{
    public string stock_id;
    public string user_to_stock;
    public string account;
    public double balance;
    public double valuationgainandloss_all;
    public double purchaseamount_all;
    public double evaluationamount_all;
}

[System.Serializable]
public class StockPurchase
{
    public string stockList_id;
    public string stock_id;
    public string stockcode;
    public double valuationgainandloss;
    public double purchaseamount;
    public double evaluationamount;
    public double stockreturns;
    public double purchaseprice;
    public int quantityheld;
}

public class httptest : MonoBehaviour
{
    void Start()
    {
        // Create user info
        UserInfo userInfo = new UserInfo
        {
            user_id = "user1",
            email = "user@example.com",
            stock_id = "account123",
            nickname = "0cha"
        };

        // Create stock account info
        StockAccount stockAccount = new StockAccount
        {
            stock_id = "account123",
            user_to_stock = "user123",
            account = "1234567890",
            balance = 1000.0,
            valuationgainandloss_all = 50.0,
            purchaseamount_all = 950.0,
            evaluationamount_all = 1050.0
        };

        // Create stock purchase info
        StockPurchase stockPurchase = new StockPurchase
        {
            stockList_id = "stock123",
            stock_id = "account123",
            stockcode = "AAPL",
            valuationgainandloss = 10.0,
            purchaseamount = 500.0,
            evaluationamount = 510.0,
            stockreturns = 0.1,
            purchaseprice = 100.0,
            quantityheld = 5
        };

        // Convert objects to JSON
        string userJson = JsonUtility.ToJson(userInfo);
        string accountJson = JsonUtility.ToJson(stockAccount);
        string purchaseJson = JsonUtility.ToJson(stockPurchase);

    }

    IEnumerator Upload(string URL, string json)
    {
        using (UnityWebRequest request = new UnityWebRequest(URL, "POST"))
        {
            // 요청 메서드를 POST로 설정합니다.
            request.method = UnityWebRequest.kHttpVerbPOST;

            // JSON 데이터를 요청에 첨부합니다.
            request.uploadHandler = new UploadHandlerRaw(System.Text.Encoding.UTF8.GetBytes(json));

            // JSON 데이터를 위한 요청 헤더를 설정합니다.
            request.SetRequestHeader("Content-Type", "application/json");

            yield return request.SendWebRequest();

            if (request.result != UnityWebRequest.Result.Success)
            {
                Debug.LogError("오류: " + request.error);
            }
        }
    }
}