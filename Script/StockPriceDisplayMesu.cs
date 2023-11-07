using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;
using TMPro; // TextMesh Pro를 사용하기 위한 네임스페이스
using System.Diagnostics;
using UnityEngine.UI;
using RainbowArt.CleanFlatUI;

public class StockPriceDisplayMesu : MonoBehaviour
{
    public string apiUrl = "http://127.0.0.1:8000/stock-price/005930.KS"; // API 엔드포인트 주소로 바꿔주세요
    public TextMeshProUGUI priceTextTMPro; // TextMeshProUGUI 컴포넌트를 저장할 변수
    public Text priceText; // Text 컴포넌트를 저장할 변수

    public Selector selector; // Selector 스크립트에 접근하기 위한 변수


    private float initialPrice; // 처음에 가져온 가격을 저장할 변수
    private float getsu; // 처음에 가져온 수량을 저장할 변수

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
                    initialPrice = response.price; // 처음에 가져온 가격을 저장

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
                    UnityEngine.Debug.LogError("JSON 파싱 오류");
                }
            }
            else
            {
                UnityEngine.Debug.LogError("API 요청 오류: " + www.error);
            }
        }
    }

    // UI 텍스트를 업데이트하는 함수
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
            UnityEngine.Debug.LogError("Text 또는 TextMeshProUGUI 컴포넌트가 지정되지 않았습니다.");
        }
    }
}

[System.Serializable]
public class StockPriceResponse9
{
    public string symbol;
    public float price;
}