using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using static Unity.Collections.AllocatorManager;

public class NetworkTest : MonoBehaviour
{
    void Start()
    {
        StartCoroutine(SendPostRequest());
    }

    IEnumerator SendPostRequest()
    {
        string url = "http://127.0.0.1:8000/save-stockList"; // FastAPI ¼­¹ö ÁÖ¼Ò

        StockData data = new StockData
        {
            account = "00244629",
            stocks = new List<Stock>
            {
                new Stock
                {
                    stockcode = "LG»ýÈ°°Ç°­",
                    valuationgainandloss = 112323,
                    purchaseamount = 121233,
                    evaluationamount = 121233,
                    stockreturns = 121233,
                    purchaseprice = 121233,
                    quantityheld = 121233
                },
                new Stock
                {
                    stockcode = "³Ø½¼",
                    valuationgainandloss = 112323,
                    purchaseamount = 121233,
                    evaluationamount = 121233,
                    stockreturns = 121233,
                    purchaseprice = 121233,
                    quantityheld = 121233
                }
            }
        };

        string jsonData = JsonUtility.ToJson(data);

        UnityWebRequest www = new UnityWebRequest(url, "POST");
        www.SetRequestHeader("Content-Type", "application/json");
        byte[] jsonBytes = System.Text.Encoding.UTF8.GetBytes(jsonData);
        www.uploadHandler = new UploadHandlerRaw(jsonBytes);
        www.downloadHandler = new DownloadHandlerBuffer();

        yield return www.SendWebRequest();

        if (www.error == null)
        {
            Debug.Log("Response: " + www.downloadHandler.text);
        }
        else
        {
            Debug.Log("Error: " + www.error);
        }
    }
}

[Serializable]
public class Stock
{
    public string stockcode;
    public int valuationgainandloss;
    public int purchaseamount;
    public int evaluationamount;
    public int stockreturns;
    public int purchaseprice;
    public int quantityheld;
}

[Serializable]
public class StockData
{
    public string account;
    public List<Stock> stocks;
}