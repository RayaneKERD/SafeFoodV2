package com.example.safefood;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.safefood.databinding.ActivityMainBinding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment((new HomeFragment()));
        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){

                case R.id.Home:
                    replaceFragment((new HomeFragment()));
                    break;
                case R.id.Favoris:
                    replaceFragment(new FavorisFragment());
                    break;
                case R.id.Scan:
                    replaceFragment(new ScanFragment());
                    scanCode();
                    break;
                case R.id.Profil:
                    replaceFragment(new ProfilFragment());
                    break;

            }



            return true;
        });



    }

    private void scanCode(){

        ScanOptions options = new ScanOptions();
        options.setPrompt("Scannez votre produit");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result ->
    {
       if(result.getContents() !=null)
       {
           AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
           builder.setTitle("Résultat");
           builder.setMessage(result.getContents());
           builder.setPositiveButton("Analysez le produit ?", new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialogInterface, int which) {

                   dialogInterface.dismiss();
               }
           }).show();
       }
    });

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_layout,fragment);
        fragmentTransaction.commit();
    }

}

