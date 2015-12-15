package pl.wspa.student.wspacitibikenyc;

import android.app.Activity;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by Karolina i Daniel on 2015-11-24.
 */
public class SettingsActivity extends Activity {
    public static class SettingsFragment extends PreferenceFragment{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.preferences);

            final CheckBoxPreference prefIntConn = (CheckBoxPreference)findPreference(SettingsUtil.KEY_INTERNET_CONNECTION),
                    prefLocConn = (CheckBoxPreference)findPreference(SettingsUtil.KEY_LOCATION_CONNECTION),
                    prefIntDia = (CheckBoxPreference)findPreference(SettingsUtil.KEY_INTERNET_DIALOG),
                    prefLocDia = (CheckBoxPreference)findPreference(SettingsUtil.KEY_LOCATION_DIALOG);

            if (prefIntConn.isChecked()) {
                prefIntDia.setEnabled(false);
            }
            if (prefLocConn.isChecked()) {
                prefLocDia.setEnabled(false);
            }

            prefIntConn.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (prefIntConn.isChecked()) {
                        prefIntDia.setChecked(true);
                        prefIntDia.setEnabled(false);
                    }
                    else
                        prefIntDia.setEnabled(true);
                    return true;
                }
            });
            prefLocConn.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    if (prefLocConn.isChecked()) {
                        prefLocDia.setChecked(true);
                        prefLocDia.setEnabled(false);
                    }
                    else
                        prefLocDia.setEnabled(true);
                    return true;
                }
            });
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}
