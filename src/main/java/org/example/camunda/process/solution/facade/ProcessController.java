package org.example.camunda.process.solution.facade;

import org.example.camunda.process.solution.ProcessConstants;
import org.example.camunda.process.solution.ProcessVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;


@RestController
@RequestMapping("/process")
public class ProcessController {

    private final static Logger LOG = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    private ZeebeClient client;

    @PostMapping("/start")
    public void startProcessInstance(@RequestBody ProcessVariables variables) {

        LOG.info("Starting process `" + ProcessConstants.BPMN_PROCESS_ID + "` with variables: " + variables);

        client.newCreateInstanceCommand()
            .bpmnProcessId(ProcessConstants.BPMN_PROCESS_ID)
            .latestVersion()
            .variables(variables)
            .send();

    }

}