package com.example.newcocktails;

import android.os.Bundle;
import android.util.Log;

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
//        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cocktails = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        getCocktailsByLetter("a");


    }

    private void getCocktailsByLetter(String letter) {
        String url = "https://www.thecocktaildb.com/api/json/v1/1/search.php?f=" + letter;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray = jsonObject.getJSONArray("drinks");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        String id, alcoholic, name, imageUrl, category, instructions;
                        List<String> ingredients = new ArrayList<>();

//                        id = jsonObject.getString("idDrink");
//                        alcoholic = jsonObject.getString("strAlcoholic");
                        name = jsonObject.getString("strDrink");
                        imageUrl = jsonObject.getString("strDrinkThumb");
                        category = jsonObject.getString("strCategory");
                        instructions = jsonObject.getString("strInstructions");

                        String ingredient;
                        for (int j = 1; j <= 16; j++) {
                            ingredient = jsonObject.getString("strIngredient" + j);
                            if (ingredient != null) {
                                ingredients.add(ingredient);
                            } else break;
                        }

                        Cocktail cocktail = new Cocktail();
//                        cocktail.setId(id);
                        cocktail.setId("1");
//                        cocktail.setAlcoholic(alcoholic.equals("Alcoholic"));
                        cocktail.setAlcoholic(true);
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
                Log.i("Tag", "Hello");
                volleyError.printStackTrace();
            }
        });
        requestQueue.add(request);
    }


}