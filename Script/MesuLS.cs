using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class MesuLS : MonoBehaviour
{
    private Button yourButton;
    private GameObject gameManager;

    void Start()
    {
        // ��ư�� �ڵ�� ã���ϴ�. ��ư�� �̸��� "YourButton"�� ���
        yourButton = GameObject.Find("ButtonMesu").GetComponent<Button>();

        // ��ư�� Ŭ�� �̺�Ʈ �����ʸ� �߰��մϴ�.
        yourButton.onClick.AddListener(TaskOnClick);

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
    void TaskOnClick()
    {
        Debug.Log("��ư�� Ŭ���Ǿ����ϴ�.");

        // GameManager ������Ʈ�� �ִ� ��� GameManager ��ũ��Ʈ�� �޼��带 ȣ���� ���� �ֽ��ϴ�.
        if (gameManager != null)
        {
            GameManager gameManagerScript = gameManager.GetComponent<GameManager>();
            if (gameManagerScript != null)
            {
                // "Samsung" ������ �����ϰ� DoSomething �޼���� �����մϴ�.
                string ecoproVariable = "LS";
                gameManagerScript.DoSomething(ecoproVariable);
            }
            else
            {
                Debug.LogError("GameManager ��ũ��Ʈ�� ã�� �� �����ϴ�.");
            }
        }
    }
}
