package com.example.tictactoe.Controller;

import com.example.tictactoe.Dto.GetBoardResponse;
import com.example.tictactoe.Dto.InitializeResponse;
import com.example.tictactoe.Dto.MoveRequest;
import com.example.tictactoe.Dto.MoveResponse;
import com.example.tictactoe.Service.TictactoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tictactoe")
public class TictactoeController {

    //Call the service using Autowired. Using autowired annotation because it automatically passing the dependency.
    @Autowired
    TictactoeService tictactoeService;


    //Example of not using @Autowired
//    @GetMapping("/start")
//    public String startGame2(@RequestParam int size) {
//        TictactoeService tictactoeService2 = new TictactoeService();
//        tictactoeService2.init(size);
//        return tictactoeService2.init(size);
//    }



    @GetMapping("/start")
    public ResponseEntity<InitializeResponse> startGame(@RequestParam int size) {
        InitializeResponse response = tictactoeService.init(size);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/move")
    public ResponseEntity<MoveResponse> move(@RequestBody MoveRequest moveRequest) {
        if(moveRequest == null){
            return ResponseEntity.ok(new MoveResponse("error","Row or Col is null", "", false));
        }
        MoveResponse movePlayer = tictactoeService.playerMove(moveRequest.getRow(), moveRequest.getCol());
        return ResponseEntity.ok(movePlayer);
    }

    @GetMapping("/getBoard")
    public ResponseEntity<GetBoardResponse> getBoard(){
        return ResponseEntity.ok(tictactoeService.getBoard());
    }
}
