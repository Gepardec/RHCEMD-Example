package at.gepardec.example.rhcemd.mp.fault;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestScopedService {

    public String doStuff() {
        return "I am from a request scoped bean executed on another thread";
    }
}
