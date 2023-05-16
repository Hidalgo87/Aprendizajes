package com.example.aprendizajes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import com.mongodb.client.MongoClient;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void IrARegistro(View view){
        Intent i = new Intent(this, Registro.class);
        startActivity(i);
    }

    public void IrAInicioSesion(View view){
        Intent i = new Intent(this, InicioSesion.class);
        startActivity(i);
    }

    public void DescargarExcel(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "DataBase", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM estudiantes", null);
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Estudiantes");
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < cursor.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(cursor.getColumnName(i));
        }
        int rowNumber = 1;
        while (cursor.moveToNext()) {
            Row row = sheet.createRow(rowNumber++);
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(cursor.getString(i));
            }
        }
        cursor.close();
        db.close();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
                // Show a dialog to ask for the user's permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                // Permission is already granted or the device is running on Android 10 or lower
                // Save the file to external storage
                File file = new File(getExternalFilesDir(null), "estudiantes.xls");
                if (file.exists()) {
                    file.delete();
                }
                FileOutputStream fileOut = new FileOutputStream(file);
                workbook.write(fileOut);
                fileOut.close();
                Toast.makeText(this, "Archivo guardado en " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            }
        } catch (IOException e){
            e.printStackTrace();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
    }
}