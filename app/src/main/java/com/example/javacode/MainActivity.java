package com.example.javacode;

import android.graphics.Color;
import android.text.SpannableString;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.text.style.ForegroundColorSpan;
import android.text.Spannable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import com.example.javacode.databinding.ActivityMainBinding;
import com.example.javacode.model.JavacodeModel;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private ActivityMainBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Inflate and get instance of binding
    binding = ActivityMainBinding.inflate(getLayoutInflater());

    // set content view to binding's root
    setContentView(binding.getRoot());
    List<JavacodeModel> listAll = new ArrayList<>();
    try {
      var input = getAssets().open("javacode.json");
      listAll =
          new Gson()
              .fromJson(
                  new InputStreamReader(input), new TypeToken<List<JavacodeModel>>() {}.getType());
      binding.rvmain.setLayoutManager(new GridLayoutManager(this, 1));
      binding.rvmain.setAdapter(
          new JavaCodeAdepter(
              listAll,
              this,
              (javacodes, pos) -> {
                JavaSyntaxHighlighter highlighter = new JavaSyntaxHighlighter();
                SpannableString highlightedCode = highlighter.highlight(javacodes.getCode());

                new MaterialAlertDialogBuilder(MainActivity.this)
                    .setTitle(javacodes.getTitle())
                    .setMessage(highlightedCode)
                    .show();
              }));

    } catch (Exception err) {

    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    this.binding = null;
  }
}
