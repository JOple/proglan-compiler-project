package com.mosj.ic.editor;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StandardEditor implements Editor {

	private Map<Class<?>, EditorModule> modules;
	
	public StandardEditor() {
		modules = new LinkedHashMap<>();
	}

	@Override
	public void run() {
		for(EditorModule module : getModules())
			module.run();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getModule(Class<T> module) {
		return (T) modules.get(module);
	}
	@Override
	public Collection<EditorModule> getModules() {
		return Collections.unmodifiableCollection(modules.values());
	}
	@Override
	public <T extends EditorModule> void addModule(T module) {
		if(module.getEditor() != null)
			throw new IllegalStateException("Module is already used by another editor");
		module.setEditor(this);
		modules.put(module.getClass(), module);
	}
	@Override
	public void removeModule(Class<?> module) {
		if(modules.containsKey(module))
			modules.get(module).setEditor(null);
		modules.remove(module);
	}
}
