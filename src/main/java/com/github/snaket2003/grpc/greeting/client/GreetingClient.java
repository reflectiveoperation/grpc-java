package com.github.snaket2003.grpc.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import static com.proto.dummy.DummyServiceGrpc.*;

public class GreetingClient {
    public static void main(String[] args) {
        System.out.println("Hello I am a gRPC Client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        System.out.println("Creating Stub...");
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc
                .newBlockingStub(channel);

        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Cristian")
                .setLastName("Perez")
                .build();

        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        final GreetResponse greetResponse = greetClient.greet(greetRequest);

        System.out.println(greetResponse.getResult());


        //Do Something

        System.out.println("Shutting down channel...");
        channel.shutdown();


    }
}
