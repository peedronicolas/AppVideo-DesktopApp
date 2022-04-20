package appvideo.vista;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

import appvideo.controlador.ControladorAppVideo;
import appvideo.lanzador.MainWindow;
import appvideo.modelo.Video;
import java.awt.BorderLayout;
import javax.swing.ScrollPaneConstants;

public class PanelMiniaturas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<Video> videoList;

	public PanelMiniaturas(List<Video> videos) {

		setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPaneVideos = new JScrollPane();
		scrollPaneVideos.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPaneVideos, BorderLayout.CENTER);

		// Creamos el modelo y añadimos los elementos
		DefaultListModel<Video> listModel = new DefaultListModel<>();
		for (Video v : videos)
			listModel.addElement(v);

		// Creamos la lista
		videoList = new JList<>(listModel);
		videoList.setCellRenderer(new VideoListRenderer());

		videoList.addMouseListener(new ActionJList(videoList));

		scrollPaneVideos.setViewportView(videoList);
	}

	public Video getVideoSeleccionado() {
		return videoList.getSelectedValue();
	}
}

class VideoListRenderer extends JLabel implements ListCellRenderer<Video> {

	private static final long serialVersionUID = 1L;

	public VideoListRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Video> list, Video video, int index,
			boolean isSelected, boolean cellHasFocus) {

		setIcon(MainWindow.getVideoWeb().getThumb(video.getURL()));
		setText("<html><body>" + video.getTitulo() + "<br>" + video.getNumReproducciones() + " visualizaciones."
				+ "</body></html>");

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		return this;
	}
}

class ActionJList extends MouseAdapter {

	protected JList<Video> list;

	public ActionJList(JList<Video> l) {
		list = l;
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {

			int index = list.locationToIndex(e.getPoint());
			if (index < 0)
				return;

			ListModel<Video> dlm = list.getModel();
			Video video = dlm.getElementAt(index);
			list.ensureIndexIsVisible(index);

			// Si hacemos doble click se reproduce el video, incrementamos el contador de
			// reproducciones y añadimos el video a los recientes del usuario.
			ControladorAppVideo.getUnicaInstancia().incrementarNumReproduccionesVideo(video);
			ControladorAppVideo.getUnicaInstancia().addVideoReciente(video);
			PanelReproductor.getUnicaInstancia().reproducirVideo(video);
		}
	}
}