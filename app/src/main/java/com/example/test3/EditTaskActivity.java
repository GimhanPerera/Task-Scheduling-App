package com.example.test3;
//import libraries
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
//--------------------------------IM/2020/004 - Bimindu Aberathna----------------------------------------------
public class EditTaskActivity extends AppCompatActivity {
    // Initialize variables for the selected date and the corresponding TextView
    private int selectedYear = 0;
    private int selectedMonth = 0;
    private int selectedDay = 0;
    private TextView selectedDateTextView, txt_date;
    private Calendar selectedDateCalendar;
    int date, month, year;

    // Declare the Task object for the updated task
    private Task updatedTask;

    // Set up the activity when it is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        // Retrieve task details from the intent
        Intent intent = getIntent();
        int taskId = intent.getIntExtra("id", -1);
        listClass object = listClass.getInstance(); // Retrieve the task list
        Task ourTask = object.getTaskById(taskId);
        String taskTitle = ourTask.getTitle();
        String taskDescription = ourTask.getDescription();
        date = ourTask.getDate();
        month = ourTask.getMonth();
        year = ourTask.getYear();
        selectedDateCalendar = Calendar.getInstance();
        selectedDateCalendar.set(Calendar.YEAR, year);
        selectedDateCalendar.set(Calendar.MONTH, month); // Note: Month is zero-based, so January is 0, February is 1, etc.
        selectedDateCalendar.set(Calendar.DAY_OF_MONTH, date);

        // Set references to the UI elements
        EditText editTaskTitle = findViewById(R.id.editTask_Title);
        EditText editTaskDescription = findViewById(R.id.editTask_Description);
        selectedDateTextView = findViewById(R.id.selectedDateTextView);

        // Populate the UI elements with task details
        editTaskTitle.setText(taskTitle);
        editTaskDescription.setText(taskDescription);
        selectedDateTextView.setText(year + "-" + month + "-" + date);

        // Handle user edits and save changes
        Button saveButton = findViewById(R.id.btn_done);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve edited task details from UI elements
                String editedTitle = editTaskTitle.getText().toString();
                String editedDescription = editTaskDescription.getText().toString();
                object.setTaskById(taskId, editedTitle, editedDescription, selectedYear, selectedMonth, selectedDay);

                // Set the result to indicate a successful update
                Intent resultIntent = new Intent();
                resultIntent.putExtra("updatedTask", updatedTask);
                setResult(RESULT_OK, resultIntent);

                // Go back to the Homepage
                Intent intent1 = new Intent(EditTaskActivity.this, HomeActivity.class);
                startActivity(intent1);
            }
        });

        // Set a click listener for the date selection button
        Button selectDateButton = findViewById(R.id.selectDateButton);
        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the DatePickerDialog only when the "Select Date" button is clicked
                showDatePickerDialog();
            }
        });

        // Set a click listener for the back button
        ImageButton btn_back = findViewById(R.id.Back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the Homepage
                Intent intent = new Intent(EditTaskActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    // Method to display the DatePickerDialog
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
