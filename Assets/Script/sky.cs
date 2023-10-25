using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class sky : MonoBehaviour
{
    public SpriteRenderer[] tiles;
    public Sprite[] ground;
    public float speed;
    private float[] tileWidth = new float[9];
    private Vector3 startPosition;
    public float hap = 0;
    public float t;
    void Start()
    {
        startPosition = tiles[0].transform.position;

        for (int i = 0; i < tiles.Length; i++)
        {
            tileWidth[i] = tiles[i].bounds.size.x;
            tiles[i].transform.position = new Vector2(hap, startPosition.y);
            tiles[i].sprite = ground[Random.Range(0, ground.Length)];
            hap += tileWidth[i];
        }
    }

    void Update()
    {
        for (int i = 0; i < tiles.Length; i++)
        {
            tiles[i].transform.Translate(new Vector2(-1, 0) * Time.deltaTime * speed);

            if (tiles[i].transform.position.x < startPosition.x - tileWidth[i])
            {
                int tmp = 0;
                if (i == 0) tmp = 8;
                else tmp = i - 1;
              //  tiles[i].transform.position = new Vector2(startPosition.x + (tiles.Length - 1) * tileWidth, startPosition.y);
                tiles[i].transform.position = new Vector2(tiles[tmp].transform.position.x+ tileWidth[tmp]-0.2f, startPosition.y);
              //  tiles[i].sprite = ground[Random.Range(0, ground.Length)];
            }
        }
    }
}