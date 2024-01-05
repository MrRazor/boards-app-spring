package cz.uhk.boardsappspring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {

    @GetMapping
    public String index() {
        return  """
                <html>
                    <header><title>Boards</title></header>
                    
                    <body>
                        Spring variant of Boards application.
                    </body>
                </html>
                """;
    }

}