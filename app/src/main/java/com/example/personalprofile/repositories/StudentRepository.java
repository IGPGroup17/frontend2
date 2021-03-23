package com.example.personalprofile.repositories;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Student;
import com.example.personalprofile.models.requestbody.RequestBodyStudent;
import com.example.personalprofile.repositories.context.StudentCrudContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentRepository extends AbstractRepository<StudentCrudContext, Student> {

    private static StudentRepository instance;

    private StudentRepository() {
    }

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }

        return instance;
    }

    @Override
    public void sendRequest(ObservingActivity<Student> activity, StudentCrudContext context) {
        attachObserver(activity);
        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());

        try {
            if (context instanceof StudentCrudContext.Create) {
                queue.addRequest(buildCreateRequest(((StudentCrudContext.Create) context).getStudent()));
            } else if (context instanceof StudentCrudContext.Read) {
                queue.addRequest(buildReadRequest(((StudentCrudContext.Read) context).getStudentId()));
            }

        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private Request<?> buildReadRequest(String studentId) {
        String url = RepositoryConstants.STUDENT_ENDPOINT + studentId;
        return new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            if (response.length() == 0) {
                notifyObservers(NotificationContext.of("NOT FOUND", null));
            } else {
                notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Student.class)));
            }
        }, Throwable::printStackTrace);
    }

    private Request<?> buildCreateRequest(RequestBodyStudent student) throws JSONException {
        return new JsonObjectRequest(Request.Method.POST, RepositoryConstants.STUDENT_ENDPOINT, new JSONObject(GSON.toJson(student)), onResponseCreate(), Throwable::printStackTrace);
    }

    private Response.Listener<JSONObject> onResponseCreate() {
        return response -> {
            try {
                Log.d("Response", response.toString(2));
            } catch (JSONException exception) {
                exception.printStackTrace();
            }
        };
    }
}
