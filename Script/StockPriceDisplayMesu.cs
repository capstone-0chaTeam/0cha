using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using TMPro; // TextMesh Pro�� ����ϱ� ���� ���ӽ����̽�
using System.Diagnostics;
using UnityEngine.UI;
using RainbowArt.CleanFlatUI;

public class StockPriceDisplayMesu : MonoBehaviour
{
    public string apiUrl = "http://127.0.0.1:8000/stock-price/005930.KS"; // API ��������Ʈ �ּҷ� �ٲ��ּ���
    public TextMeshProUGUI priceTextTMPro; // TextMeshProUGUI ������Ʈ�� ������ ����
    public Text priceText; // Text ������Ʈ�� ������ ����

    public Selector selector; // Selector ��ũ��Ʈ�� �����ϱ� ���� ����


    private float initialPrice; // ó���� ������ ������ ������ ����
    private float getsu; // ó���� ������ ������ ������ ����

    void Start()
    {
        StartCoroutine(FetchStockPriceRealtime());
        //UnityEngine.Debug.Log(initialPrice);


    }

    IEnumerator FetchStockPriceRealtime()
    {
        using (WWW www = new WWW(apiUrl))
        {
            yield return www;

            if (string.IsNullOrEmpty(www.error))
            {
                StockPriceResponse response = JsonUtility.FromJson<StockPriceResponse>(www.text);

                if (response != null)
                {
                    initialPrice = response.price; // ó���� ������ ������ ����

                    //priceTextTMPro.text = response.price.ToString("N0");
                    //UpdatePriceText(initialPrice);

                    if (selector != null)
                    {
                        // Set the value in the Selector script
                        selector.SetInitialPrice(initialPrice);
                    }


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
    }

    // UI �ؽ�Ʈ�� ������Ʈ�ϴ� �Լ�
    void UpdatePriceText(float price)
    {
        if (priceTextTMPro != null)
        {
            priceTextTMPro.text = price.ToString("N0");
        }
        else if (priceText != null)
        {
            priceText.text = price.ToString("N0");
        }
        else
        {
            UnityEngine.Debug.LogError("Text �Ǵ� TextMeshProUGUI ������Ʈ�� �������� �ʾҽ��ϴ�.");
        }
    }
}

[System.Serializable]
public class StockPriceResponse9
{
    public string symbol;
    public float price;
}