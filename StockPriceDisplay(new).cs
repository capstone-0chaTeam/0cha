using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
using TMPro;

public class StockPriceDisplay : MonoBehaviour
{
    public TextMeshProUGUI priceText;
    public string apiUrl = "http://127.0.0.1:8000/stock-price/005930.KS";

    void Start()
    {
        StartCoroutine(FetchStockPriceRealtime());
    }

    IEnumerator FetchStockPriceRealtime()
    {
        while (true)
        {
            UnityWebRequest www = UnityWebRequest.Get(apiUrl);
            yield return www.SendWebRequest();

            if (www.result == UnityWebRequest.Result.Success)
            {
                StockPriceResponse response = JsonUtility.FromJson<StockPriceResponse>(www.downloadHandler.text);

                if (response != null)
                {
                    priceText.text = response.price.ToString("N0");
                }
                else
                {
                    Debug.LogError("JSON �Ľ� ����");
                }
            }
            else
            {
                Debug.LogError("API ��û ����: " + www.error);
            }

            www.Dispose(); // UnityWebRequest�� �޸𸮿��� ����

            yield return new WaitForSeconds(5.0f);
        }
    }
}

[System.Serializable]
public class StockPriceResponse
{
    public string symbol;
    public float price;
}
