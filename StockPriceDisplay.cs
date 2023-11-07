using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using TMPro; // TextMesh Pro�� ����ϱ� ���� ���ӽ����̽�
using System.Diagnostics;

public class StockPriceDisplay : MonoBehaviour
{
    public TextMeshProUGUI priceText; // UI�� �ֽ� ������ ǥ���� TextMeshProUGUI ���
    public string apiUrl = "http://127.0.0.1:8000/stock-price/005930.KS"; // API ��������Ʈ �ּҷ� �ٲ��ּ���

    void Start()
    {
        StartCoroutine(FetchStockPriceRealtime());
    }

    IEnumerator FetchStockPriceRealtime()
    {
        while (true)
        {
            using (WWW www = new WWW(apiUrl))
            {
                yield return www;

                if (string.IsNullOrEmpty(www.error))
                {
                    StockPriceResponse response = JsonUtility.FromJson<StockPriceResponse>(www.text);

                    if (response != null)
                    {
                        priceText.text =  response.price.ToString("N0");
                    }
                    else
                    {
                        UnityEngine.Debug.LogError("JSON �Ľ� ����");
                    }
                }
                else
                {
                    UnityEngine.Debug.LogError("API ��û ����: " + www.error);
                }
            }

            yield return new WaitForSeconds(5.0f); // ���� ���, 5�ʸ��� ������Ʈ
        }
    }
}

[System.Serializable]
public class StockPriceResponse
{
    public string symbol;
    public float price;
}