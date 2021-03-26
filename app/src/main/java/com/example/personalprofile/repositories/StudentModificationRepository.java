package com.example.personalprofile.repositories;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.personalprofile.activities.meta.ObservingActivity;
import com.example.personalprofile.http.VolleyQueue;
import com.example.personalprofile.models.Student;
import com.example.personalprofile.models.requestbody.RequestBodyStudent;
import com.example.personalprofile.repositories.context.StudentModificationContext;
import com.example.personalprofile.repositories.meta.AbstractRepository;
import com.example.personalprofile.repositories.meta.RepositoryConstants;
import com.example.personalprofile.repositories.meta.observer.NotificationContext;

import org.json.JSONException;
import org.json.JSONObject;

public class StudentModificationRepository extends AbstractRepository<StudentModificationContext, Student> {

    private static StudentModificationRepository instance;

    private StudentModificationRepository() {
    }

    public static StudentModificationRepository getInstance() {
        if (instance == null) {
            instance = new StudentModificationRepository();
        }

        return instance;
    }

    @Override
    public void sendRequest(ObservingActivity<Student> activity, StudentModificationContext context) {
        attachObserver(activity);
        VolleyQueue queue = VolleyQueue.getInstance(activity.getApplicationContext());

        try {
            if (context instanceof StudentModificationContext.Create) {
                queue.addRequest(buildCreateRequest(((StudentModificationContext.Create) context).getStudent()));
            } else if (context instanceof StudentModificationContext.Read) {
                queue.addRequest(buildReadRequest(((StudentModificationContext.Read) context).getStudentId()));
            } else if (context instanceof StudentModificationContext.Delete) {
                queue.addRequest(buildDeleteRequest((StudentModificationContext.Delete) context));
            }

        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    private Request<?> buildDeleteRequest(StudentModificationContext.Delete context) {
        String url = RepositoryConstants.STUDENT_ENDPOINT + context.getStudentId();
        return new JsonObjectRequest(Request.Method.DELETE, url, null, response -> {}, Throwable::printStackTrace);
    }

    private Request<?> buildReadRequest(String studentId) {
        String url = RepositoryConstants.STUDENT_ENDPOINT + studentId;
        return new JsonObjectRequest(Request.Method.GET, url, null,
                response -> notifyObservers(NotificationContext.of(GSON.fromJson(response.toString(), Student.class))),
                this::handleError);
    }

    private void handleError(VolleyError error) {
        int statusCode = error.networkResponse.statusCode;
        if (statusCode == 404) {
            notifyObservers(NotificationContext.of("NOT FOUND", null));
        } else {
            error.printStackTrace();
        }
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
