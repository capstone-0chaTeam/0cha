using UnityEngine;

public class CoinMove : MonoBehaviour
{
    public float coinSpeed = 0;
    private Vector3 startPosition = new Vector3(26f, 0f); // Set the initial position here with a fixed X coordinate
    private float destroyXPosition = -10f; // Set the X position below which the object should be destroyed

    private void OnEnable()
    {
        // Randomly set the Y coordinate within the range of -1f to 2f
        startPosition.y = Random.Range(-1f, 2f);
        transform.position = startPosition;
    }

    void Update()
    {
        transform.Translate(Vector3.left * Time.deltaTime * coinSpeed);

        if (transform.position.x < destroyXPosition)
        {
            gameObject.SetActive(false);
        }
    }

}