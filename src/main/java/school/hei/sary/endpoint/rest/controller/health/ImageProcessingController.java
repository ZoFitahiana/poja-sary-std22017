package school.hei.sary.endpoint.rest.controller.health;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.hei.sary.service.event.ImageProcessingService;

@RestController
@RequestMapping("/api/images")
public class ImageProcessingController {
    private final ImageProcessingService imageProcessingService;

    public ImageProcessingController(ImageProcessingService imageProcessingService) {
        this.imageProcessingService = imageProcessingService;
    }

    @PutMapping(value = "/process-black-and-white/{key}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> processToBlackAndWhite(@PathVariable String key, @RequestBody byte[] image) {
        byte[] processedImage = imageProcessingService.processToBlackAndWhite(image, key);
        if (processedImage.length > 0) {
            return ResponseEntity.ok().body(processedImage);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}

