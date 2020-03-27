package net.runelite.client.plugins.advancednotifications.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import net.runelite.client.plugins.advancednotifications.AdvancedNotificationsPlugin;
import net.runelite.client.plugins.advancednotifications.Notification;
import net.runelite.client.util.ImageUtil;

public class EnabledButton extends JLabel
{
	private static final ImageIcon ENABLED_ICON;
	private static final ImageIcon ENABLED_HOVER_ICON;
	private static final ImageIcon DISABLED_ICON;
	private static final ImageIcon DISABLED_HOVER_ICON;

	private final Notification notification;

	static
	{
		final BufferedImage enabledIcon
			= ImageUtil.getResourceStreamFromClass(AdvancedNotificationsPlugin.class, "enabled_icon.png");
		ENABLED_ICON = new ImageIcon(enabledIcon);
		ENABLED_HOVER_ICON = new ImageIcon(ImageUtil.alphaOffset(enabledIcon, 0.53f));

		final BufferedImage disabledIcon
			= ImageUtil.getResourceStreamFromClass(AdvancedNotificationsPlugin.class, "disabled_icon.png");
		DISABLED_ICON = new ImageIcon(disabledIcon);
		DISABLED_HOVER_ICON = new ImageIcon(ImageUtil.alphaOffset(disabledIcon, 0.53f));
	}

	public EnabledButton(AdvancedNotificationsPlugin plugin, Notification notification)
	{
		this.notification = notification;

		setIcon(notification.isEnabled() ? ENABLED_ICON : DISABLED_ICON);

		addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				notification.setEnabled(!notification.isEnabled());
				updateIcon();
				plugin.updateConfig();
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				setIcon(notification.isEnabled() ? ENABLED_HOVER_ICON : DISABLED_HOVER_ICON);
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				setIcon(notification.isEnabled() ? ENABLED_ICON : DISABLED_ICON);
			}
		});
	}

	private void updateIcon()
	{
		if (getIcon() == ENABLED_ICON || getIcon() == DISABLED_ICON)
		{
			setIcon(notification.isEnabled() ? ENABLED_ICON : DISABLED_ICON);
		}
		else
		{
			setIcon(notification.isEnabled() ? ENABLED_HOVER_ICON : DISABLED_HOVER_ICON);
		}
	}
}