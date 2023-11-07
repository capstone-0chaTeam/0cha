using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MedoLGjun : MonoBehaviour
{
    private Button medoButton;
    private GameObject gameManager;

    void Start()
    {
        // ��ư�� �ڵ�� ã���ϴ�. ��ư�� �̸��� "YourButton"�� ���
        medoButton = GameObject.Find("ButtonMedo").GetComponent<Button>();

        // ��ư�� Ŭ�� �̺�Ʈ �����ʸ� �߰��մϴ�.
        medoButton.onClick.AddListener(TaskOnClick_Medo);

        gameManager = GameObject.Find("GameManager");
        if (gameManager != null)
        {
            // GameManager�� ã���� �� ������ �ڵ带 �ۼ��մϴ�.
            Debug.Log("GameManager�� ã��.");
        }
        else
        {
            // GameManager�� ã�� ������ �� ó��
            Debug.LogError("GameManager�� ã�� �� �����ϴ�.");
        }
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    void TaskOnClick_Medo()
    {
        Debug.Log("�ŵ� ��ư�� Ŭ���Ǿ����ϴ�.");

        // GameManager ������Ʈ�� �ִ� ��� GameManager ��ũ��Ʈ�� �޼��带 ȣ���� ���� �ֽ��ϴ�.
        if (gameManager != null)
        {
            GameManager gameManagerScript = gameManager.GetComponent<GameManager>();
            if (gameManagerScript != null)
            {
                // "Samsung" ������ �����ϰ� DoSomething �޼���� �����մϴ�.
                string samsungVariable = "LGjun";
                gameManagerScript.DoSomething1(samsungVariable);
            }
            else
            {
                Debug.LogError("GameManager ��ũ��Ʈ�� ã�� �� �����ϴ�.");
            }
        }
    }
}
