using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class overcoin : MonoBehaviour
{
    // Start is called before the first frame update
    public Text totalScoreText;

    void Start()
    {
        // PlayerPrefs�� ����Ͽ� ����� �� ������ ������
        int totalScore = PlayerPrefs.GetInt("TotalScore", 0);

        // totalScoreText�� �� ������ ǥ��
        totalScoreText.text = "�� ȹ�� �ݾ� : " + totalScore + "��";
    }
}
