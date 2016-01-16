package pl.edu.agh.to.app.controller;

import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.agh.to.app.model.Solution;
import pl.edu.agh.to.app.model.Student;
import pl.edu.agh.to.app.model.Task;
import pl.edu.agh.to.app.persistence.SolutionDao;
import pl.edu.agh.to.app.persistence.StudentDao;
import pl.edu.agh.to.app.persistence.TaskDao;

import java.io.*;
import java.util.List;

@RestController
public class TaskController {

    private TaskDao taskDao = new TaskDao();
    private SolutionDao solutionDao = new SolutionDao();
    private StudentDao studentDao = new StudentDao();

    @RequestMapping(path="api/student/tasks/{id}", method = RequestMethod.GET)
    public Task showTask(@PathVariable long id) throws IOException {
        Task task = taskDao.getTask(id);
        return task;
    }

    @RequestMapping(path="api/student/tasks", method = RequestMethod.GET)
    public List<Task> showTasks() throws IOException {
        return taskDao.getTasks();
    }

    @RequestMapping(path="api/student/tasks/{id}/solutions/new", method = RequestMethod.POST)
    public Solution createSolution(@PathVariable long id, @RequestParam("solution_file") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            Task task = taskDao.getTask(id);
            Student author = studentDao.getStudent(1);

            Solution sol = new Solution(null, "NOT_TESTED", author, task, null);
            solutionDao.saveSolution(sol);

            int status = sendFile(file, sol.getId());
            if (status == 200){
                return sol;
            } else {
                return null;
            }
        } else {
            return null;
        }


    }

    private int sendFile(MultipartFile mfile, long id) throws IOException {
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

        HttpPost httppost = new HttpPost("http://172.29.140.38:80/solutions/" + id);
        File file = new File(mfile.getOriginalFilename());
        File file = File.createTempFile(mfile.getOriginalFilename(), null);
        mfile.transferTo(file);

        MultipartEntity mpEntity = new MultipartEntity();
        ContentBody cbFile = new FileBody(file);
        mpEntity.addPart("solution_file", cbFile);

        httppost.setEntity(mpEntity);
        System.out.println("executing request " + httppost.getRequestLine());
        CloseableHttpResponse response = httpclient.execute(httppost);
        org.apache.http.HttpEntity resEntity = response.getEntity();

        if (resEntity != null) {
            System.out.println(EntityUtils.toString(resEntity));
        }
        if (resEntity != null) {
            resEntity.consumeContent();
        }

        file.delete();

        httpclient.getConnectionManager().shutdown();
        return response.getStatusLine().getStatusCode();
    }

}