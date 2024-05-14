package com.example.newcocktails;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private CocktailAdapter cocktailAdapter;
    private ArrayList<Cocktail> cocktails;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recycleViewCocktails);
        int height = recyclerView.getHeight();
        recyclerView.getLayoutParams().height = height;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cocktails = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        getCocktailsByLetter("a");

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.listButton);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.listButton) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemId == R.id.randomButton) {
                startActivity(new Intent(this, RandomPageActivity.class));
            } else if (itemId == R.id.findButton) {
                startActivity(new Intent(this, FindActivity.class));
            } else if (itemId == R.id.worldButton) {
                startActivity(new Intent(this, WorldActivity.class));
            }
            return true;
        });


    }

    private void getCocktailsByLetter(String letter) {
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + letter;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("drinks");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                        String id, alcoholic, name, imageUrl, category, instructions;
                        List<String> ingredients = new ArrayList<>();

                        id = jsonObject1.getString("idDrink");
                        alcoholic = jsonObject1.getString("strAlcoholic");
                        name = jsonObject1.getString("strDrink");
                        imageUrl = jsonObject1.getString("strDrinkThumb");
                        category = jsonObject1.getString("strCategory");
                        instructions = jsonObject1.getString("strInstructions");

                        String ingredient;
                        for (int j = 1; j <= 15; j++) {
                            ingredient = jsonObject1.getString("strIngredient" + j);
                            if (ingredient != null) {
                                ingredients.add(ingredient);
                            } else break;
                        }

                        Cocktail cocktail = new Cocktail();
                        cocktail.setId(id);
                        cocktail.setAlcoholic(alcoholic.equals("Alcoholic"));
                        cocktail.setName(name);
                        cocktail.setImageUrl(imageUrl);
                        cocktail.setCategory(category);
                        cocktail.setInstructions(instructions);
                        cocktail.setIngredients(ingredients);

                        cocktails.add(cocktail);
                    }

                    cocktailAdapter = new CocktailAdapter(MainActivity.this, cocktails);
                    recyclerView.setAdapter(cocktailAdapter);

                } catch (JSONException e) {
                    throw new RuntimeException(e);

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyError.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    public void goRandomCocktail(View view) {
        Intent intent = new Intent(this, RandomPageActivity.class);
        startActivity(intent);
    }


}