package appvideo.persistencia;

import java.util.List;
import appvideo.modelo.Video;

public interface IAdaptadorVideoDAO {

	public void registrarVideo(Video video);

	public void borrarVideo(Video video);

	public void modificarVideo(Video video);

	public Video recuperarVideo(int codigo);

	public List<Video> recuperarTodosVideos();
}