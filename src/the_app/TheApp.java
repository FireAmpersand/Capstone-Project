

package the_app;

import backend_models.ServerConnection;
import frontend_viewcontroller.*;


public class TheApp implements Runnable {

    @Override
    public void run() {
        BackendModelSetup theBackendModel = new BackendModelSetup();
        MainViewDisplay theMainViewDisplay = new MainViewDisplay(theBackendModel);
        ModelsAndViewsController theMainViewsController = new ModelsAndViewsController(theBackendModel, theMainViewDisplay);
        ServerConnection.checkIsUpdated();

        theMainViewDisplay.setVisible(true);
    }
}