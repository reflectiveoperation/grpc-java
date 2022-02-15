package com.github.snaket2003.grpc.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

import static com.proto.greet.GreetServiceGrpc.*;

public class GreetServiceImpl extends GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        final Greeting greeting = request.getGreeting();
        final String firstName = greeting.getFirstName();

        final String result = "Hello " + firstName;
        final GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();

        //Send Response
        responseObserver.onNext(response);

        //Complete RPC
        responseObserver.onCompleted();

        //        super.greet(request, responseObserver);
    }
}
