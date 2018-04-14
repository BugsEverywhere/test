//package com.siemens.allkindsoftest.video;
//
//import com.xuggle.xuggler.ICodec;
//import com.xuggle.xuggler.IContainer;
//import com.xuggle.xuggler.IStream;
//import com.xuggle.xuggler.IStreamCoder;
//
//import java.io.File;
//
///**
// * Created with IntelliJ IDEA.
// * User: Simon
// * Date:
// * Time:
// *
// * @author nali
// */
//public class VideoTestMain {
//
//    public static void main(String[] args) {
//
//        IContainer container = IContainer.make();
//
//        container.open("/Users/nali/Downloads/wKgJ8VqfjJjiEI1VCcBX01IqTcs642.mp4", IContainer.Type.READ, null);
//
//        int numStreams = container.getNumStreams();
//
//        for (int i = 0; i < numStreams; i++) {
//
//            IStream stream = container.getStream(i);
//
//            IStreamCoder coder = stream.getStreamCoder();
//
//            System.out.println("*** Start of Stream Info ***");
//
//            System.out.printf("stream %d: ", i);
//
//            System.out.printf("type: %s; ", coder.getCodecType());
//
//            System.out.printf("codec: %s; ", coder.getCodecID());
//
//            System.out.printf("duration: %s; ", stream.getDuration());
//
//            System.out.printf("start time: %s; ", container.getStartTime());
//
//            System.out.printf("timebase: %d/%d; ",
//
//                    stream.getTimeBase().getNumerator(),
//
//                    stream.getTimeBase().getDenominator());
//
//            System.out.printf("coder tb: %d/%d; ",
//
//                    coder.getTimeBase().getNumerator(),
//
//                    coder.getTimeBase().getDenominator());
//
//            System.out.println();
//
//
//            if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO) {
//
//                System.out.printf("sample rate: %d; ", coder.getSampleRate());
//
//                System.out.printf("channels: %d; ", coder.getChannels());
//
//                System.out.printf("format: %s", coder.getSampleFormat());
//
//            } else if (coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO) {
//
//                System.out.printf("width: %d; ", coder.getWidth());
//
//                System.out.printf("height: %d; ", coder.getHeight());
//
//                System.out.printf("format: %s; ", coder.getPixelType());
//
//                System.out.printf("frame-rate: %5.2f; ", coder.getFrameRate().getDouble());
//
//            }
//
//
//            System.out.println();
//
//            System.out.println("*** End of Stream Info ***");
//
//
//        }
//
//    }
//
//}
