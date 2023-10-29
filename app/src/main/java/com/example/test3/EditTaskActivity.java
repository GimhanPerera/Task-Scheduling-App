package com.example.test3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.test3.Model.Task;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditTaskActivity extends AppCompatActivity {
    private int selectedYear = 0;
    private int selectedMonth = 0;
    private int selectedDay = 0;
    private TextView selectedDateTextView, txt_date;
    private Calendar selectedDateCalendar;
    int date,month,year;

    private Task updatedTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Intent intent = getIntent();
        int taskId = intent.getIntExtra("id", -1);
        listClass object = listClass.getInstance();//get task list
        Task ourTask = object.getTaskById(taskId);
        String taskTitle = ourTask.getTitle();
        String taskDescription = ourTask.getDescription();
        date = ourTask.getDate();
        month = ourTask.getMonth();
        year = ourTask.getYear();
        selectedDateCalendar = Calendar.getInstance();
        selectedDateCalendar.set(Calendar.YEAR, date);
        selectedDateCalendar.set(Calendar.MONTH, month); // Note: Month is zero-based, so January is 0, February is 1, etc.
        selectedDateCalendar.set(Calendar.DAY_OF_MONTH, year);

        // Populate the UI elements with task details
        EditText editTaskTitle = findViewById(R.id.editTask_Title);
        EditText editTaskDescription = findViewById(R.id.editTask_Description);
        //EditText txt_date = findViewById(R.id.selectedDateTextView);
        selectedDateTextView = findViewById(R.id.selectedDateTextView);

        // Populate other UI elements for date and time
        editTaskTitle.setText(taskTitle);
        editTaskDescription.setText(taskDescription);
        selectedDateTextView.setText(year+"-"+month+"-"+date);

        // Handle user edits and save changes
        Button saveButton = findViewById(R.id.btn_done);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve edited task details from UI elements
                String editedTitle = editTaskTitle.getText().toString();
                String editedDescription = editTaskDescription.getText().toString();
                object.setTaskById(taskId,editedTitle,editedDescription,10,10,10);//NEED TO SET REAL DATE
                // Create an updated task
                //updatedTask = new Task(taskId, editedTitle, editedDescription, selectedYear, selectedMonth, selectedDay);

                // 3. Set the result to indicate a successful update
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedTask", updatedTask);
                setResult(RESULT_OK, resultIntent);

                // 4. Go back to Homepage
                Intent intent1=new Intent(EditTaskActivity.this,HomeActivity.class);
                startActivity(intent1);
            }
        });

        Button selectDateButton = findViewById(R.id.selectDateButton);
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the DatePickerDialog only when the "Select Date" button is clicked
                showDatePickerDialog();
            }
        });

        ImageButton btn_back = findViewById(R.id.Back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditTaskActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        // Initialize the DatePickerDialog with the current date
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                R.style.DatePickerDialogStyle,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Handle the selected date
                        selectedDateCalendar.set(year, month, dayOfMonth);

                        // Extract and store the selected date components
                        selectedYear = selectedDateCalendar.get(Calendar.YEAR);
                        selectedMonth = selectedDateCalendar.get(Calendar.MONTH);
                        selectedDay = selectedDateCalendar.get(Calendar.DAY_OF_MONTH);

                        // Format the selected date as needed and display it in the TextView
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                        String formattedDate = dateFormat.format(selectedDateCalendar.getTime());
                        selectedDateTextView.setText(formattedDate);
                    }
                },
                year,
                month,
                date
        );

        datePickerDialog.show();
    }
}
